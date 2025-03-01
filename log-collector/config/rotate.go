package config

import (
	"fmt"
	"gopkg.in/natefinch/lumberjack.v2"
	"os"
	"path/filepath"
	"time"
)

// RotateLogger는 자정마다 새 로그 파일로 롤링하는 작업을 수행
func RotateLogger(logger *lumberjack.Logger, folderName string, fileName string) {
	go func() {
		for {
			// 현재 시간과 자정 시간 설정
			next := GetNextMidnightInSeoul()

			// 자정까지 대기
			time.Sleep(time.Until(next))

			// 새 로그 파일 이름 (기본 파일명 + 날짜)
			baseDirectory := os.Getenv("LOG_DIRECTORY")
			if folderName != "" {
				baseDirectory = filepath.Join(baseDirectory, folderName)
			}
			newLogFile := filepath.Join(baseDirectory, fmt.Sprintf("%s-%s.log", fileName, GetSeoulTimeYesterday().Format("2006-01-02")))

			logger.Filename = newLogFile

			// 롤링 수행
			err := logger.Rotate()
			if err != nil {
				return
			}
		}
	}()
}
